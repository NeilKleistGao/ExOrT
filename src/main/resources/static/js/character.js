$(document).ready(function() {
    $("#create").click(function() {
        $("#id").val(null);
        $("#name").val("");
        $("#area").val("");
        $("#school").val("");

        $(".form-group").hide();

        $("#setting_modal").modal("show");
    });

    $("#submit").click(function() {
        if ($("#name").val() === "") {
            $("#name").addClass("is-invalid");
            return;
        }

        if ($("#id").val() != null) {
            let participation_data = {
                 id: $("#id").val()
             };
             let children = $("div.form-group").find("input");
             for (let i = 0; i < children.length; i++) {
                let aid = children[i].id;
                participation_data[aid.substring(aid.indexOf("-") + 1)] = children[i].checked;
                children[i].checked = false;
             }

             $.post("/update/participation", participation_data);
        }

        $.post("/update/character", {
            id: $("#id").val(),
            name: $("#name").val(),
            area: $("#area").val(),
            school: $("#school").val()
        }, function() {
            location.reload();
        });
        $("#setting_modal").modal("hide");
    });

    $("#table").contextMenu({
        selector: "tr",
        callback: function(key) {
            if (key === "update") {
                $("#id").val($(this).children("td.data-id")[0].innerHTML);
                $("#name").val($(this).children("td.data-name")[0].innerHTML);
                $("#area").val($(this).children("td.data-area")[0].innerHTML);
                $("#school").val($(this).children("td.data-school")[0].innerHTML);

                $(".form-group").show();

                $.get("/get/participation/aid/" + $("#id").val(), function(data, status) {
                    for (let id of data) {
                        $("#check-" + id).prop("checked", true);
                    }
                    $("#setting_modal").modal("show");
                });
            }
            else if (key === "delete") {
                $.post("/delete/character", {
                    id: $(this).children("td.data-id")[0].innerHTML,
                }, function() {
                    location.reload();
                });
            }
        },
        items: {
            "update": {name: "编辑"},
            "delete": {name: "移除"}
        }
    });
});