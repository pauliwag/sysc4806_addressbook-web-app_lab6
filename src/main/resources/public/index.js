/**
 * @author Paul Roode
 */
$(document).ready(function() {

    // override all of the forms
    $('#create').submit(onSubmit);
    $('#view').submit(onSubmit);
    $('#add').submit(onSubmit);

    // override form submission with Ajax calls
    function onSubmit(event) {
        event.preventDefault();
        var url = $(this).closest('form').attr('action');
        var data = $(this).closest('form').serialize();
        var type = $(this).closest('form').attr('method');
        $.ajax({
            url: url,
            type: type,
            data: data,
            success: onSuccess
        });
    }

    // append a div that will comprise the results
    $('#body').append('<div id="resultsDiv"></div>');

    function onSuccess(data) {
        $('#resultsDiv').empty().append(data);
        $('a').remove();
        $('#buddy').submit(onSubmit);
    }

});