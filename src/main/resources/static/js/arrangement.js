$(document).ready(function() {
    $("#create").click(function() {
        let now = new Date();
        let today = (now.getYear() + 1900) + "-" + (now.getMonth() + 1 < 10 ? "0" + (now.getMonth() + 1) : now.getMonth()) + "-" + now.getDate();

        $("#id").val(null);
        $("#name").val("");
        $("#start-date").val(today);
        $("#end-date").val(today);
        $("#repeat").val(0);
        $("#start-time").val("07:00");
        $("#end-time").val("19:00");

        $("#setting_modal").modal("show");
    });

    $("#submit").click(function() {
        for (let label of ["#name", "#start-date", "#end-date", "#repeat", "#start-time", "#end-time"]) {
            if ($(label).val() === "") {
                $(label).addClass("is-invalid");
                return;
            }
        }


        $.post("/update/arrangement", {
            id: $("#id").val(),
            name: $("#name").val(),
            start_date: $("#start-date").val(),
            end_date: $("#end-date").val(),
            repeat: $("#repeat").val(),
            start_time: $("#start-time").val(),
            end_time: $("#end-time").val()
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
                $("#start-date").val($(this).children("td.data-start-date")[0].innerHTML);
                $("#end-date").val($(this).children("td.data-end-date")[0].innerHTML);
                $("#repeat").val($(this).children("td.data-repeat")[0].innerHTML);
                $("#start-time").val($(this).children("td.data-start-time")[0].innerHTML);
                $("#end-time").val($(this).children("td.data-end-time")[0].innerHTML);

                $("#setting_modal").modal("show");
            }
            else if (key === "delete") {
                $.post("/delete/arrangement", {
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