$(() => {
    /**
     * sticky header
     */
    window.onscroll = function () {
        stickyFunction()
    };

    let header = document.querySelector("#stickyHeader");
    let sticky = header.offsetTop;

    function stickyFunction() {
        if (window.pageYOffset > sticky) {
            header.classList.add("sticky");
        } else {
            header.classList.remove("sticky");
        }
    }
});