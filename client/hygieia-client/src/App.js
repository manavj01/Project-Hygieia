import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import SignUp from './components/Auth/SignUp';
import './index.css'
import './App.css';
function App() {
  return (
    <Router>
      <div className='App'>
        <Routes>
          <Route path='/' element={<SignUp />} />
        </Routes>
      </div>
    </Router>
    // <div className="text-3xl font-bold text-blue-500">
    //   Tailwind is working!
    // </div>
  );
}

export default App;
