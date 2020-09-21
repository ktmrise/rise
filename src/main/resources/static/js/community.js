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
        }),//schoolList是你要提交是json字符串
        success: function (response) {
            console.log(response);
            if (response.code === 200) {
                $("#reply").val("");
            } else {
                if (response.code === 4000) {
                    let isAccepted = confirm(response.message);
                    if (isAccepted) {
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