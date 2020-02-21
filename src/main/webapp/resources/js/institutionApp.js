$(() => {
    let form = $("div#institution-form");
    let link = $("a.edit-institution");
    $("button.add-institution").on("click", () => {
        form.attr("hidden", false);
    });
    link.on("click", (event) =>{
        form.attr("hidden", false);
        form.find("#id").val($(event.target).closest("tr").children().eq(0).text());
        form.find("#name").val($(event.target).closest("tr").children().eq(1).text());
        form.find("#description").val($(event.target).closest("tr").children().eq(2).text());

    })
});