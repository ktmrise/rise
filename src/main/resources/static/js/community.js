function reply() {
    let questionId = $("#questionId").val();
    let content = $("#reply").val();

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json", //必须这样写
        dataType: "json",
        data: JSON.stringify({
            parentId: questionId,
            content: content,
            type: 1
        }),
        success: function (response) {
            console.log(response);
            if (response.code === 200) {
                location.reload();
            } else {
                if (response.code === 4000) {
                    let isAccepted = confirm(response.message);
                    if (isAccepted) {
                        localStorage.setItem("close", "true");
                        window.open("https://github.com/login/oauth/authorize?client_id=51a6b073b4b9e92a2159&redirect_uri=http://localhost:8887/callback&scope=user&state=1"
                        );
                    }
                } else {
                    alert(response.message);
                }


            }
        }

    })

}


function collapseComment(e) {
    let id = e.getAttribute("data-id");
    let comment = $("#" + id);

    if (comment.hasClass("in")) {
        comment.removeClass('in');
    } else {
        comment.addClass("in");

    }

}