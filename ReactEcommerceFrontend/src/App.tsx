import { Link, Route, Routes } from 'react-router-dom';
import HomePage from './features/catalog/HomePage';
import ProductDetailPage from './features/catalog/ProductDetailPage';
import CartPage from './features/cart/CartPage';
import CheckoutPage from './features/checkout/CheckoutPage';
import OrdersPage from './features/orders/OrdersPage';
import LoginPage from './features/auth/LoginPage';

function App() {
  return (
    <div style={{ fontFamily: 'Arial, sans-serif', padding: '20px', maxWidth: '1100px', margin: '0 auto' }}>
      <nav style={{ display: 'flex', gap: '15px', marginBottom: '20px', borderBottom: '1px solid #ddd', paddingBottom: '10px' }}>
        <Link to="/">Home</Link>
        <Link to="/products/1">Product</Link>
        <Link to="/cart">Cart</Link>
        <Link to="/checkout">Checkout</Link>
        <Link to="/orders">Orders</Link>
        <Link to="/login">Login</Link>
      </nav>

      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/products/:id" element={<ProductDetailPage />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/checkout" element={<CheckoutPage />} />
        <Route path="/orders" element={<OrdersPage />} />
        <Route path="/login" element={<LoginPage />} />
      </Routes>
    </div>
  );
}

export default App;
