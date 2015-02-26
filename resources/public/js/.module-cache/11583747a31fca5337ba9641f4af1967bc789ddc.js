var CommentBox = React.createClass({displayName: "CommentBox",
  render: function() {
    return (
      React.createElement("div", {className: "commentBox"}, 
        React.createElement("h1", null, "Comments"), 
        React.createElement(CommentList, null), 
        React.createElement(CommentForm, null)
      )
    );
  }
});

var CommentList = React.createClass({displayName: "CommentList",
  render: function() {
    return (
      React.createElement("div", {className: "commentList"}, 
        "CommentList"
      )
        );
    }
  });

var CommentForm = react.createClass({
  render: function() {
    return (
      React.createElement("div", {className: "commentForm"}, 
        "CommentForm"
      )
        );
    }
  });

React.render(
  React.createElement(CommentBox, null),
  document.getElementById('content')
);
