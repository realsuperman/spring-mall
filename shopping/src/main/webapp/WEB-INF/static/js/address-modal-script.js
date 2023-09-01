$(document).ready(function() {
    var modalVisible = false;

    $("#address").click(function() {
        $("#addressModal").modal();
        modalVisible = true;
    });

    $('#addressModal').on('hidden.bs.modal', function() {
        if (modalVisible) {
            history.pushState(null, null, ''); // Prevent further back navigation
            modalVisible = false;
        }
    });

    $(window).on('popstate', function() {
        if (modalVisible) {
            $("#addressModal").modal('hide');
            modalVisible = false;
            history.pushState(null, null, ''); // Prevent further back navigation
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const addressForm = document.querySelector(".address-update-form");
    const addressModal = document.getElementById("addressModal");

    addressForm.addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent the default form submission behavior

        const updateAddress = document.getElementById("update_address").value;
        const updateAddressDetail = document.getElementById("update_address_detail").value;

        const combinedAddress = updateAddress + " " + updateAddressDetail;
        document.getElementById("address").value = combinedAddress;

        // Close the modal and prevent further back navigation
        history.pushState(null, null, '');
        modalVisible = false; // Reset modalVisible
        addressModal.style.display = "none"; // Not recommended, see explanation above

        // Submit the form (assuming you have a hidden submit button)
        document.getElementById("hidden_submit_button").click();
    });
});