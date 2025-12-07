function showModal() {
    var urlParams = new URLSearchParams(window.location.search);
    var status = urlParams.get('status');

    // Check if status is 'error' and show the modal
    if (status === 'error') {
        // Assuming you're using plain HTML/CSS for the modal
        // document.getElementById('errorModal').style.display = 'block';
        
        // If using Bootstrap modals, you would use:
        $('#errorModal').modal('show');
    } else if (status === 'success') {
        // Optionally handle the success status if needed
    }
}

// Use DOMContentLoaded to ensure the script runs after DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    showModal();
});