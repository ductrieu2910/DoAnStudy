
    document.querySelector("form").addEventListener("submit", function () {
    document.getElementById("loading").style.display = "block";
});


    document.querySelector("form").addEventListener("submit", function (event) {
        event.preventDefault();
        const formData = new FormData(this);

        fetch(this.action, {
            method: "POST",
            body: formData
        }).then(response => {
            if (response.ok) {
                alert("Khóa học đã được lưu thành công!");
                location.reload(); // Hoặc chuyển hướng trang nếu cần thiết
            } else {
                alert("Có lỗi xảy ra, vui lòng thử lại.");
            }
        }).catch(error => console.error("Error:", error));
    });
