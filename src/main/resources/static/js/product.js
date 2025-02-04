function loadProducts() {
    const activePage = document.querySelector('.pagination .active');
    const page = activePage ? parseInt(activePage.dataset.page, 10) || 0 : 0;
    const size = parseInt(document.getElementById('pageSize').value, 10);
    const sortBy = document.getElementById('sortBy').value;

    // بررسی مقادیر
    if (isNaN(page) || isNaN(size) || !sortBy) {
        showError(new Error('مقادیر صفحه‌بندی یا مرتب‌سازی نامعتبر هستند.'));
        return;
    }

    const url = `/products?page=${encodeURIComponent(page)}&size=${encodeURIComponent(size)}&sortBy=${encodeURIComponent(sortBy)}&fragment=true`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در دریافت داده‌ها');
            }
            return response.text();
        })
        .then(html => {
            const productsTableContainer = document.getElementById('products-table-container');
            if (productsTableContainer) {
                productsTableContainer.innerHTML = html;
                initPagination();
                initDeleteButtons();
            }
        })
        .catch(error => showError(error));
}

function handleProductFormSubmit(e) {
    e.preventDefault();

    const product = {
        id: document.getElementById('productId').value,
        name: document.getElementById('name').value.trim(),
        brand: document.getElementById('brand').value.trim()
    };

    if (!product.name) {
        alert(document.getElementById('validationErrorText').textContent);
        return;
    }

    fetch('/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(product)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در ارسال درخواست');
            }
            return response.json();
        })
        .then(() => {
            const modal = bootstrap.Modal.getInstance(document.getElementById('productModal'));
            if (modal) {
                modal.hide();
            }
            loadProducts();
        })
        .catch(error => showError(error));
}

function handleProductDelete(e) {
    const btn = e.target.closest('.btn-danger');
    const confirmText = btn.dataset.confirmText || 'آیا از حذف این محصول اطمینان دارید؟';

    if (!confirm(confirmText)) return;

    const productId = btn.dataset.id;

    fetch(`/products/${productId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('خطا در حذف محصول!!!');
            }
            loadProducts();
        })
        .catch(error => showError(error));
}

// ==================== مدیریت رویدادها ====================
function initProductModal() {
    const productModal = document.getElementById('productModal');
    if (productModal) {
        productModal.addEventListener('show.bs.modal', function (e) {
            const btn = e.relatedTarget;
            const row = btn.closest('tr');

            if (btn.dataset.id) {
                document.getElementById('productId').value = btn.dataset.id;
                document.getElementById('name').value = row.cells[0].textContent.trim();
                document.getElementById('brand').value = row.cells[1].textContent.trim();
            } else {
                document.getElementById('productForm').reset();
                document.getElementById('productId').value = '';
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
            loadProducts();
        });
    });
}

function initSortAndPageSize() {
    const pageSize = document.getElementById('pageSize');
    const sortBy = document.getElementById('sortBy');

    if (pageSize) {
        pageSize.addEventListener('change', loadProducts);
    }
    if (sortBy) {
        sortBy.addEventListener('change', loadProducts);
    }
}

function initDeleteButtons() {
    document.querySelectorAll('.btn-danger').forEach(btn => {
        btn.addEventListener('click', handleProductDelete);
    });
}

// ==================== مقداردهی اولیه ====================
document.addEventListener('DOMContentLoaded', () => {
    loadProducts();
    const productForm = document.getElementById('productForm');
    if (productForm) {
        productForm.addEventListener('submit', handleProductFormSubmit);
    }

    initProductModal();
    initPagination();
    initSortAndPageSize();
    initDeleteButtons();
});