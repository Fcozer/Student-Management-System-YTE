import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './Login';
import Admin from './admin/Admin';
import Student from './students/Student';
import StudentView from './components/students/StudentView';
import AssistantView  from './components/assistants/AssistantView';
import AcademicianView  from './components/academicians/AcademicianView';
import LessonView from './components/lessons/LessonView';
import Assistant from './assistant/Assistant';
import Academician from './academician/Academician';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import ListLesson from "./components/lessons/ListLesson";

function App() {
  return (
      <div className="App">
        <ToastContainer
            position="top-right"
            autoClose={5000}
            hideProgressBar={false}
            newestOnTop={false}
            closeOnClick
            rtl={false}
            pauseOnFocusLoss
            draggable
            pauseOnHover
        />
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Login/>} />
            <Route path="/Login" element={<Login/>} />
            <Route path="/Admin" element={<Admin/>} />
            <Route path ="/Student" element={<Student/>} />
            <Route path ="/Assistant" element={<Assistant/>} />
            <Route path ="/Academician" element={<Academician/>} />
            <Route path='/StudentView' element= {<StudentView/>}/>
            <Route path='/AssistantView' element= {<AssistantView/>}/>
            <Route path='/AcademicianView' element= {<AcademicianView/>}/>
            <Route path='/LessonView' element= {<LessonView/>}/>
            <Route path='/ListLesson' element= {<ListLesson/>}/>
          </Routes>
        </BrowserRouter>
      </div>
  );
}

export default App;
