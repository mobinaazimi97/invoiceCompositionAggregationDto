function loadOrderItems() {
    const activePage = document.querySelector('.pagination .active');
    const page = activePage ? parseInt(activePage.dataset.page, 10) || 0 : 0;
    const size = parseInt(document.getElementById('pageSize').value, 10);
    const sortBy = document.getElementById('sortBy').value;

    // بررسی مقادیر
    if (isNaN(page) || isNaN(size) || !sortBy) {
        showError(new Error('مقادیر صفحه‌بندی یا مرتب‌سازی نامعتبر هستند.'));
        return;
    }

    const url = `/orderItems?page=${encodeURIComponent(page)}&size=${encodeURIComponent(size)}&fragment=true`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در دریافت داده‌ها');
            }
            return response.text();
        })
        .then(html => {
            const orderItemsTableContainer = document.getElementById('orderItems-table-container');
            if (orderItemsTableContainer) {
                orderItemsTableContainer.innerHTML = html;
                initPagination();
                initDeleteButtons();
            }
        })
        .catch(error => showError(error));
}

function handleOrderItemFormSubmit(e) {
    e.preventDefault();

    const orderItem = {
        id: document.getElementById('orderItemId').value,
        order: document.getElementById('order').value,
        product: document.getElementById('product').value.trim(),
        quantity: document.getElementById('quantity').value.trim(),
    };

    if (!orderItem.order) {
        alert(document.getElementById('validationErrorText').textContent);
        return;
    }

    fetch('/orderItems', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderItem)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در ارسال درخواست');
            }
            return response.json();
        })
        .then(() => {
            const modal = bootstrap.Modal.getInstance(document.getElementById('orderItemModal'));
            if (modal) {
                modal.hide();
            }
            loadOrderItems();
        })
        .catch(error => showError(error));
}

function handleOrderItemDelete(e) {
    const btn = e.target.closest('.btn-danger');
    const confirmText = btn.dataset.confirmText || 'آیا از حذف این مورد از فاکتور اطمینان دارید؟';

    if (!confirm(confirmText)) return;

    const orderItemId = btn.dataset.id;

    fetch(`/orderItems/${orderItemId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در حذف مورد از فاکتور!!!');
            }
            loadOrderItems();
        })
        .catch(error => showError(error));
}

// ==================== مدیریت رویدادها ====================
function initOrderItemModal() {
    const orderItemModal = document.getElementById('orderItemModal');
    if (orderItemModal) {
        orderItemModal.addEventListener('show.bs.modal', function (e) {
            const btn = e.relatedTarget;
            const row = btn.closest('tr');

            if (btn.dataset.id) {
                document.getElementById('orderItemId').value = btn.dataset.id;
                document.getElementById('order').value = row.cells[0].textContent.trim();
                document.getElementById('product').value = row.cells[1].textContent.trim();
                document.getElementById('quantity').value = row.cells[2].textContent.trim();
            } else {
                document.getElementById('orderItemForm').reset();
                document.getElementById('orderItemId').value = '';
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
            loadOrderItems();
        });
    });
}

function initSortAndPageSize() {
    const pageSize = document.getElementById('pageSize');
    // const sortBy = document.getElementById('sortBy');

    if (pageSize) {
        pageSize.addEventListener('change', loadOrderItems);
    }
    // if (sortBy) {
    //     sortBy.addEventListener('change', loadOrderItems);
    // }
}

function initDeleteButtons() {
    document.querySelectorAll('.btn-danger').forEach(btn => {
        btn.addEventListener('click', handleOrderDelete);
    });
}

// ==================== مقداردهی اولیه ====================
document.addEventListener('DOMContentLoaded', () => {
    loadOrderItems();
    const orderItemForm = document.getElementById('orderItemForm');
    if (orderItemForm) {
        orderItemForm.addEventListener('submit', handleOrderItemFormSubmit);
    }

    initOrderItemModal();
    initPagination();
    // initSortAndPageSize();
    initDeleteButtons();
});