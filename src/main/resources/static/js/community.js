function reply() {
    let questionId = $("#questionId").val();
    let content = $("#reply").val();
    if (content === null || content === '') {
        alert('回复内容不能为空');
        return;
    }

    extracted(questionId, content, 1);
}



function collapseComment(e) {
    let id = e.getAttribute("data-id");
    let comment = $("#" + id);

    let collapse = e.getAttribute('data-collapse');
    let count = e.getAttribute('collapse-count');
    console.log(count);
    console.log(collapse);

    if (collapse==='in') {
        comment.removeClass('in');
        e.setAttribute('data-collapse', '');
    } else {
        comment.addClass("in");

        e.setAttribute("data-collapse", 'in');
        //获取二级评论
        $.getJSON('/selectTwoComments', {id: id}, function (response) {


            var html = "";

            $.each(response, function (k, v) {
                html += '<div class="media"  style="margin-top: 38px;">\n' +
                    '                                    <div class="media-left">\n' +
                    '                                        <a href="#">\n' +
                    '                                            <img style="border-radius: 30px;" class="media-object"\n' +
                    '                                                 src=' + v.authorAvatarUrl + ' alt="..." width="60px">\n' +
                    '                                        </a>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="media-body" style="line-height: 36px">\n' +
                    '                                        <h4 class="media-heading">' + v.authorName + '</h4>\n' +
                    '                                        <span>' + v.content + '</span>\n' +
                    '                                        <div>\n' +
                    '\n' +
                    '                                            <span class="pull-right">' + v.createTime + '</span>\n' +
                    '                                        </div>\n' +
                    '\n' +
                    '\n' +
                    '                                    </div>\n' +
                    '                                    <hr>\n' +
                    '\n' +
                    '                                </div>';

            });
            if (count === 'true') {
                comment.append(html);
                comment.append('<div class="form-group" style="margin-top: 10px">\n' +
                    '                                    <input type="text"  class="form-control" id="comment"\n' +
                    '                                           placeholder="评论一下...">\n' +
                    '                                    <button  onclick="insertTwoComment(' + id + ')" type="submit" style="margin: 10px 0" class="btn btn-success pull-right">评论\n' +
                    '                                    </button>\n' +
                    '                                </div>')
                e.setAttribute('collapse-count', 'false');
            }


        });


    }

}

function insertTwoComment(parentId) {

    let content = $("#comment").val();


    extracted(parentId, content, 2);


}


function extracted(parentId, content, type) {
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
                        window.open("https://github.com/login/oauth/authorize?client_id=e6c3b60df22c5922d3e1&redirect_uri=http://localhost:8887/callback&scope=user&state=1"
                        // window.open("https://github.com/login/oauth/authorize?client_id=51a6b073b4b9e92a2159&redirect_uri=http://106.75.145.192/callback&scope=user&state=1"
                        );
                    }
                } else {
                    alert(response.message);
                }


            }
        }

    })
}


function selectTag(e) {
    let tagValue = e.getAttribute('data-tag');

    let val = $("#tag").val();
    if (val === '') {
        $("#tag").val(tagValue);
    } else {
        if (val.indexOf(tagValue) !== -1) {
            alert('已添加该标签');
        } else {
            $("#tag").val(val + ',' + tagValue);

        }
    }
}


// function hideTag() {
//
//         $("#tag-nav").attr('hidden', 'hidden');
// }

function showTag() {
    $("#tag-nav").attr('hidden', null);

}

var el;


