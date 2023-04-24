import React from 'react'
import ReactDOM from 'react-dom/client'
import GlobalContextProvider from './components/utils/global.context'
import HomeContextProvider from './components/utils/home.context'
import App from './app/App'
import ProductContextProvider from './components/utils/product.context'

ReactDOM.createRoot(document.getElementById('root')).render(
    <GlobalContextProvider>
      <HomeContextProvider>
        <ProductContextProvider>
          <App />
        </ProductContextProvider>
      </HomeContextProvider>
    </GlobalContextProvider>

)