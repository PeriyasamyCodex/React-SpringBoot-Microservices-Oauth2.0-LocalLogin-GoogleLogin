
import { createRoot } from 'react-dom/client'
import './index.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { Provider } from 'react-redux'
import store from './store/index.ts'
import RootLayout, { loader as rootLoader } from './pages/RootLayout.tsx'
import LandingPage, { loader as placesLoader } from './pages/LandingPage.tsx'
import AttractionsPage, { loader as attractionLoader } from './pages/AttractionsPage.tsx'
import LoginPage, { loginActions } from './pages/login/LoginPage.tsx'
import OAuth2RedirectHandler from './pages/oauth2/Oauth2RedirectHandler.tsx'
import Logout from './pages/login/Logout.tsx'

const router = createBrowserRouter([{
  path: "/home",
  element: <RootLayout />,
  loader: rootLoader,
  children: [
    { index: true,
      element: <LandingPage />, 
      loader:  (localStorage.getItem('token')) && placesLoader, 
      hydrateFallbackElement: <h2 className='text-white'>Loading Places...</h2>,      
    },
    { path: ':placeId',
      element: <AttractionsPage/>,
      loader: (localStorage.getItem('token')) && attractionLoader,
      hydrateFallbackElement: <h2 className='text-white'>Loading Attractions...</h2>,     
    }
  ]
},
    { path: '/',
      element: <LoginPage/>,
      action: loginActions
    },
    { path: '/oauth2/redirect',
      element: <OAuth2RedirectHandler/>,
          
    },
    { path: '/logout',
      element: <Logout/>,
          
    }])
export const queryClient = new QueryClient();
createRoot(document.getElementById('root')).render(

  <Provider store={store}><QueryClientProvider client={queryClient}><RouterProvider router={router} /></QueryClientProvider></Provider>
  ,
)
