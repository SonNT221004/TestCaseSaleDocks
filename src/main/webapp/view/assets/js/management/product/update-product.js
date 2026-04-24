$(document).ready(function () {
    $('#update-product').on('submit', function (e) {
        e.preventDefault();

        var formData = new FormData(this);
        
        var productId = formData.get('id');

        $.ajax({
            type: 'POST',
            url: $(this).attr('action'),
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                // MISSING: success notification removed - POST-3 requires notifying user of successful update
                window.location.href = 'product-detail?id=' + productId;
            },
            error: function (response) {

            }
        });
    });
});