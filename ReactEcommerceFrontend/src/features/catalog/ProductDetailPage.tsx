import { useParams } from 'react-router-dom';

function ProductDetailPage() {
  const { id } = useParams();

  return (
    <div>
      <h2>Product Detail</h2>
      <p>Showing product with id: {id}</p>
      <p>This page maps to the Catalog Service and can later call a real backend API.</p>
      <button type="button">Add to Cart</button>
    </div>
  );
}

export default ProductDetailPage;
