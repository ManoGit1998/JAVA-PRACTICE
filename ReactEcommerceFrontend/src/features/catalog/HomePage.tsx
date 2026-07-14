function HomePage() {
  const products = [
    { id: 1, name: 'Laptop', price: '$999' },
    { id: 2, name: 'Smartphone', price: '$699' },
    { id: 3, name: 'Headphones', price: '$199' },
  ];

  return (
    <div>
      <h2>Catalog</h2>
      <p>This page represents the Catalog Service UI.</p>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(180px, 1fr))', gap: '15px' }}>
        {products.map((product) => (
          <div key={product.id} style={{ border: '1px solid #ddd', padding: '15px', borderRadius: '8px' }}>
            <h3>{product.name}</h3>
            <p>{product.price}</p>
            <a href={`/products/${product.id}`}>View Details</a>
          </div>
        ))}
      </div>
    </div>
  );
}

export default HomePage;
