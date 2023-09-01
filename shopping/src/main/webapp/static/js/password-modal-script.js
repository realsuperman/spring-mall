
    $(document).ready(function(){
    var modalVisible = false;

    $("#my_btn").click(function(){
    $("#myModal").modal();
    modalVisible = true;
});

    $('#myModal').on('hidden.bs.modal', function () {
    modalVisible = false;
});

    $(window).on('popstate', function() {
    if (modalVisible) {
    $("#myModal").modal('hide');
    modalVisible = false;
    history.pushState(null, null, ''); // Prevent further back navigation
}
});
});



