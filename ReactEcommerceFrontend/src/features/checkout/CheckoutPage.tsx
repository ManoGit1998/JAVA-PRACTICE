function CheckoutPage() {
  return (
    <div>
      <h2>Checkout</h2>
      <p>This page is designed for the Checkout flow across Cart, Inventory, Payment, and Order services.</p>
      <div style={{ display: 'grid', gap: '10px', maxWidth: '500px' }}>
        <input placeholder="Address" />
        <input placeholder="Payment Method" />
        <button type="button">Place Order</button>
      </div>
    </div>
  );
}

export default CheckoutPage;
