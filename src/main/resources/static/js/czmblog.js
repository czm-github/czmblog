/*提交回复*/
function question() {
    var questionId = $("#question_id").val();
    var content = $("#comment_context").val();
    comment2target(questionId,1,content)
}

function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    var isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=2859958f9f059979ed3a&redirect_uri=" + document.location.origin + "/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", "true");
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

function comment(e) {
    var commentId = e.getAttribute("data-id");
    var content = $("#input-" + commentId).val();
    comment2target(commentId, 2, content);
}

/*二级评论*/
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-"+id);
    if(comments.hasClass("in")){
        /*折叠collapse*/
        comments.removeClass("in");
        e.classList.remove("active");
    }else{
        /*展开collapse in*/
        var comment2Div = comments;
        if (comment2Div.children().length != 1) {
            comments.addClass("in");
            e.classList.add("active");
        }else{
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index,comment) {
                    var UlElement = $("<ul/>", {
                        "class": "list-group"
                    });
                    var LiElement = $("<li/>", {
                        "class": "list-group-item"
                    }).append(mediaElement);
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-thumbnail img-wh img-responsive",
                        "src": comment.user.avatarUrl
                    }));
                    var mediaBodyLeft = $("<div/>", {
                        "class": "media-body"
                    }).append($("<span/>", {
                        "class": "media-heading",
                        "html": comment.user.name+"："
                    }).append($("<span/>", {
                        "html": comment.content
                    })));
                    var mediaBodyRight = $("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
                    });
                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyLeft).append(mediaBodyRight);
                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12"
                    }).append(UlElement);

                    UlElement.append(LiElement);
                    LiElement.append(mediaElement);
                    comment2Div.prepend(commentElement);
                });
            });
            comments.addClass("in");
            e.classList.add("active");
        }

    }
}

function showSelectTag() {
    $("#select-tag").show();
}

function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}