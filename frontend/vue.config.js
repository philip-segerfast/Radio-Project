// vue.config.js
module.exports = {
  // https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    port: 3000,
    proxy: {
      '/rest': {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true,
        secure: false
      },
      '/api': {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true,
        secure: false
      }
    }
  }
}
