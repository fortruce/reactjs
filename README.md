# reactjs

This is an example of the **ReactJS** tutorial in Javascript with a Clojure backend. 

## Project Setup

Below are the steps used to create this project from a base lein template.

### Create the Base

`lein new reactjs`

### Add lein-ring, ring, and compojure

We will use *ring* and *compojure* as the basis for the underlying Clojure web server. *lein-ring* will be used to aid in development by running a local server.

Add the following dependencies to the `project.clj`:

```
...
:dependencies [...
               [compojure "1.3.2"]
               [ring/ring-core "1.3.2"]
               ...]
               
:plugins [[lein-ring "0.9.2"]]
```

### Minimal Server

Once the dependencies have been added, we will create a base server to serve an index.html, all of our resources, and a 404 page.

Add a handler for *lein-ring* in `project.clj`:

```
...
:ring {:handler reactjs.core/handler}
...
```

Modify `core.clj` to the following:

```
(ns reactjs.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.util.response :refer [resource-response]]))

(defroutes app
  (GET "/" [] (resource-response "index.html" {:root "public"}))
  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))

(def handler
  (handler/site app))
```

This requires the namespaces we need from *compojure* and *ring*, sets up our basic routes (return index.html for the base-url, serve up our static resources (from the 'resources/public' dir), and serve 404 otherwise), and wraps our routes in the default site middleware.

Now, we need to also provide the `index.html` page. Create the file `resources/public/index.html` with the following:

```
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Hello React</title>
    <script src="js/react.js"></script>
  </head>
  <body>
    <div id="content"></div>
    <script src="js/comment.js"></script>
  </body>
</html>
```

This `index.html` file references two *js* resources we haven't created yet. Lets do that now.

```
react.js should be downloaded from the React site and placed in "resources/public/js/".
```

"comment.js` is actually our React jsx that has been compiled down into js for us by *react-tools* which we will do next.

### Compile Our JSX

First, install react-tools:

`npm install -g react-tools`

Create our new directory structure:

```
src/
  js/
    comment.js
  clj/
    reactjs/
      core.clj
```

Now, since we moved `core.clj`, we must tell leiningen where our source is. Edit `project.clj`:

```
...
:source-paths ["src/clj"]
...
```

Finally, set up jsx to be auto-compiled to our resources directory.

```
jsx --watch src/js/ resources/public/js/
```

### Finally Rejoice

Place the following first step of the React tutorial into `comment.js`:

```
var CommentBox = React.createClass({
  render: function() {
    return (
      <div className="commentBox">
        Hello, world! I am a CommentBox.
      </div>
    );
  }
});
React.render(
  <CommentBox />,
  document.getElementById('content')
);
```

**NOTE:** For some reason I had to restart the `jsx --watch` command after first creating `comment.js`. I suspect it doesn't recognize file creation, but it does work on subsequent modifications.

This should then be autocompiled by jsx into `comment.js` in our resources directory.

Just run our server and browse to the homepage:

`lein ring server-headless`

Viola!

## License

Insert whatever license means free game here. Seriously, use whatever you like.
