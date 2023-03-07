$(document).ready(() => {
    Date.prototype.toDateInputValue = (function() {
        var local = new Date(this);
        local.setMinutes(this.getMinutes() - this.getTimezoneOffset());
        return local.toJSON().slice(0,10);
    });
    if (msg == null) {
        $('#when').val(new Date().toDateInputValue());
    }

    $('form input, form textarea').blur(function(){
        $(this).next().hide('slow')
    })

    $('h1, h4').each(function(){
        $(this).css('display','none')
    })

    $('h1, h4').each(function(){
        $(this).show(1000)
    })
});
