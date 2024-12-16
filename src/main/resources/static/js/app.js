
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


    document.addEventListener("DOMContentLoaded", function () {
        // Lấy giá trị hiện tại từ phần tử với id="price"
        let priceElement = document.getElementById('price');
        let price = priceElement.innerText.trim();

        // Hàm định dạng giá
        function formatPrice(price) {
            let formattedPrice = parseFloat(price).toFixed(2); // Định dạng thành 2 chữ số sau dấu phẩy
            return formattedPrice.replace(/\B(?=(\d{3})+(?!\d))/g, ","); // Thêm dấu phẩy cho hàng nghìn
        }

        // Định dạng lại giá và cập nhật vào phần tử
        priceElement.innerText = formatPrice(price);
    });

    document.addEventListener('DOMContentLoaded', () => {
        const toggle = document.querySelector('[data-bs-toggle="collapse"]');
        const dropdown = document.querySelector('#user-dropdown');

        toggle.addEventListener('click', () => {
            console.log('Collapse toggle clicked!');
        });

        dropdown.addEventListener('shown.bs.collapse', () => {
            console.log('Dropdown opened');
        });

        dropdown.addEventListener('hidden.bs.collapse', () => {
            console.log('Dropdown closed');
        });
    });