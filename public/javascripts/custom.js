$(function(){
    $(".delete-link").click(function(){
        $.post($(this).attr('href'));
        $(this).closest($(this).data('delete-parent')).fadeOut();
        return false;
    });
});