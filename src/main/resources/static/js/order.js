function loadOrders() {
    const activePage = document.querySelector('.pagination .active');
    const page = activePage ? parseInt(activePage.dataset.page, 10) || 0 : 0;
    const size = parseInt(document.getElementById('pageSize').value, 10);
    const sortBy = document.getElementById('sortBy').value;

    // بررسی مقادیر
    if (isNaN(page) || isNaN(size) || !sortBy) {
        showError(new Error('مقادیر صفحه‌بندی یا مرتب‌سازی نامعتبر هستند.'));
        return;
    }

    const url = `/orders?page=${encodeURIComponent(page)}&size=${encodeURIComponent(size)}&sortBy=${encodeURIComponent(sortBy)}&fragment=true`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در دریافت داده‌ها');
            }
            return response.text();
        })
        .then(html => {
            const ordersTableContainer = document.getElementById('orders-table-container');
            if (ordersTableContainer) {
                ordersTableContainer.innerHTML = html;
                initPagination();
                initDeleteButtons();
            }
        })
        .catch(error => showError(error));
}

function handleOrderFormSubmit(e) {
    e.preventDefault();

    const order = {
        id: document.getElementById('orderId').value,
        orderDate: document.getElementById('orderDate').value,
        price: document.getElementById('price').value.trim(),
        customer: document.getElementById('customer').value.trim(),
    };

    if (!order.orderDate) {
        alert(document.getElementById('validationErrorText').textContent);
        return;
    }

    fetch('/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(order)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در ارسال درخواست');
            }
            return response.json();
        })
        .then(() => {
            const modal = bootstrap.Modal.getInstance(document.getElementById('orderModal'));
            if (modal) {
                modal.hide();
            }
            loadOrders();
        })
        .catch(error => showError(error));
}

function handleOrderDelete(e) {
    const btn = e.target.closest('.btn-danger');
    const confirmText = btn.dataset.confirmText || 'آیا از حذف این فاکتور اطمینان دارید؟';

    if (!confirm(confirmText)) return;

    const orderId = btn.dataset.id;

    fetch(`/orders/${orderId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در حذف فاکتور!!!');
            }
            loadOrders();
        })
        .catch(error => showError(error));
}

// ==================== مدیریت رویدادها ====================
function initOrderModal() {
    const orderModal = document.getElementById('orderModal');
    if (orderModal) {
        orderModal.addEventListener('show.bs.modal', function (e) {
            const btn = e.relatedTarget;
            const row = btn.closest('tr');

            if (btn.dataset.id) {
                document.getElementById('orderId').value = btn.dataset.id;
                document.getElementById('orderDate').value = row.cells[0].textContent.trim();
                document.getElementById('price').value = row.cells[1].textContent.trim();
                document.getElementById('customer').value = row.cells[2].textContent.trim();
            } else {
                document.getElementById('orderForm').reset();
                document.getElementById('orderId').value = '';
            }
        });
    }
}

function initPagination() {
    document.querySelectorAll('.page-link').forEach(link => {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            document.querySelectorAll('.page-item').forEach(item => item.classList.remove('active'));
            this.closest('.page-item').classList.add('active');
            loadOrders();
        });
    });
}

function initSortAndPageSize() {
    const pageSize = document.getElementById('pageSize');
    const sortBy = document.getElementById('sortBy');

    if (pageSize) {
        pageSize.addEventListener('change', loadOrders);
    }
    if (sortBy) {
        sortBy.addEventListener('change', loadOrders);
    }
}

function initDeleteButtons() {
    document.querySelectorAll('.btn-danger').forEach(btn => {
        btn.addEventListener('click', handleOrderDelete);
    });
}

// ==================== مقداردهی اولیه ====================
document.addEventListener('DOMContentLoaded', () => {
    loadOrders();
    const orderForm = document.getElementById('orderForm');
    if (orderForm) {
        orderForm.addEventListener('submit', handleOrderFormSubmit);
    }

    initOrderModal();
    initPagination();
    initSortAndPageSize();
    initDeleteButtons();
});