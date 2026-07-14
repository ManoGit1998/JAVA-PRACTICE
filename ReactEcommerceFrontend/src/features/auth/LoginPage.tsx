function LoginPage() {
  return (
    <div>
      <h2>Login</h2>
      <p>This page connects to the Auth Service in the backend architecture.</p>
      <form style={{ display: 'grid', gap: '10px', maxWidth: '400px' }}>
        <input placeholder="Email" />
        <input placeholder="Password" type="password" />
        <button type="button">Login</button>
      </form>
    </div>
  );
}

export default LoginPage;
