function reply() {
    let questionId = $("#questionId").val();
    let content = $("#reply").val();

    extracted(questionId,content,1);
}

let count = 0;
let globalId='';

function collapseComment(e) {
    let id = e.getAttribute("data-id");
    let comment = $("#" + id);
    console.log(comment);
    if (globalId!==id) {
        count = 0;
        globalId = id;

    }

    if (comment.hasClass("in")) {
        comment.removeClass('in');
    } else {
        comment.addClass("in");
        //获取二级评论
        $.getJSON('/selectTwoComments', {id: id}, function (response) {


            var html = "";

            $.each(response, function (k, v) {
                html += '<div class="media"  style="margin-top: 38px;">\n' +
                    '                                    <div class="media-left">\n' +
                    '                                        <a href="#">\n' +
                    '                                            <img style="border-radius: 30px;" class="media-object"\n' +
                    '                                                 src='+v.authorAvatarUrl +' alt="..." width="60px">\n' +
                    '                                        </a>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="media-body" style="line-height: 36px">\n' +
                    '                                        <h4 class="media-heading">'+v.authorName+'</h4>\n' +
                    '                                        <span>'+v.content+'</span>\n' +
                    '                                        <div>\n' +
                    '\n' +
                    '                                            <span class="pull-right">'+v.createTime+'</span>\n' +
                    '                                        </div>\n' +
                    '\n' +
                    '\n' +
                    '                                    </div>\n' +
                    '                                    <hr>\n' +
                    '\n' +
                    '                                </div>';

            });
            if (count === 0) {
                comment.append(html);
                comment.append('<div class="form-group" style="margin-top: 10px">\n' +
                    '                                    <input type="text"  class="form-control" id="comment"\n' +
                    '                                           placeholder="评论一下...">\n' +
                    '                                    <button  onclick="insertTwoComment('+id+')" type="submit" style="margin: 10px 0" class="btn btn-success pull-right">评论\n' +
                    '                                    </button>\n' +
                    '                                </div>')
            }
            count++;

        });


    }

}

function insertTwoComment(parentId) {

    let content = $("#comment").val();


    extracted(parentId,content,2);


}



function extracted(parentId,content,type) {
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json", //必须这样写
        dataType: "json",
        data: JSON.stringify({
            parentId: parentId,
            content: content,
            type: type
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
