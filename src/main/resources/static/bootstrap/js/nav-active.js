$('#rs-header .nav-menu a')
    .on('click', function () {
        $('#rs-header .nav-menu')
            .find('li.active')
            .removeClass('active');
        $(this).parent('li')
            .addClass('active');
    });