$(document).ready(function() {
    $("div.admin-tab-menu>div.list-group>a").click(function(e) {
        e.preventDefault();
        $(this).siblings('a.active').removeClass("active");
        $(this).addClass("active");
        var index = $(this).index();
        $("div.admin-tab>div.admin-tab-content").removeClass("active");
        $("div.admin-tab>div.admin-tab-content").eq(index).addClass("active");
    });
});