// vue.config.js
module.exports = {
  // https://cli.vuejs.org/config/#devserver-proxy
  devServer: {
    port: 3000,
    proxy: {
      '/api': {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true,
        secure: false
      },
      '/rest': {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true,
        secure: false
      },
      '/auth': {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true,
        secure: false
      },
      '/login': {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true,
        secure: false
      },
      '/logout': {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true,
        secure: false
      },
      '/auth/register': {
        target: 'http://localhost:5000',
        ws: true,
        changeOrigin: true,
        secure: false
      }
    }
  }
}
