$(document).ready(function() {
    $("#alert").hide();

    $("#save").click(function() {
        let list = $("input");
        let data = {};
        for (let item of list) {
            console.log(item);
            data[item.id] = item.value;
        }

        $.post("/update/settings", data, function() {
            $("#alert").show();
        });
    });

    $("#sync").click(function() {
        $("#log_modal").modal("show");
    });

    $("#submit").click(function() {
        $.post("/sync", {
            username: $("#username").val(),
            password: $("#password").val()
        }, function() {
            $("#log_modal").modal("hide");
        });
    });
});