$(function(){
    $(".delete-link").click(function(){
        $.post($(this).attr('href'));
        $(this).closest($(this).data('delete-parent')).fadeOut();
        return false;
    });

    $('.big-button, .data-link').click(function(){
        location.href = $(this).data('href');
    });
});