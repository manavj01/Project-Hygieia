import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import './index.css'
import './App.css';
import LoginSignUp from './Components/LoginSignUp/LoginSignUp';
function App() {
  return (
    <Router>
      <div className='App'>
        <Routes>
          <Route path='/' element={<LoginSignUp />} />
        </Routes>
      </div>
    </Router>
    // <div className="text-3xl font-bold text-blue-500">
    //   Tailwind is working!
    // </div>
  );
}

export default App;
