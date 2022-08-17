import React, {Component} from 'react';
import './App.css';
import Admin from './AdminPage/Admin';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import StudentList from "./AdminPage/StudentList";
import StudentEdit from "./AdminPage/StudentEdit";
import AcademicianList from "./AdminPage/AcademicianList";
import AcademicianEdit from "./AdminPage/AcademicianEdit";
import AssistantEdit from "./AdminPage/AssistantEdit";
import AssistantList from "./AdminPage/AssistantList";
import LessonList from "./AdminPage/LessonList";
import LessonEdit from "./AdminPage/LessonEdit";
import AcademicianPage from "./AcademicianPage/AcademicianPage";
import Login from "./Login";
import ExamEdit from "./AcademicianPage/ExamEdit";
import ExamList from "./AcademicianPage/ExamList";


class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Login}/>

                    <Route path='/admin' exact={true} component={Admin}/>
                    <Route path='/students' exact={true} component={StudentList}/>
                    <Route path='/students/:id' component={StudentEdit}/>
                    <Route path='/academicians' exact={true} component={AcademicianList}/>
                    <Route path='/academicians/:id' component={AcademicianEdit}/>
                    <Route path='/assistant' exact={true} component={AssistantList}/>
                    <Route path='/assistant/:id' component={AssistantEdit}/>
                    <Route path='/Lessons' exact={true} component={LessonList}/>
                    <Route path='/Lessons/:id' component={LessonEdit}/>

                    <Route path='/academicianPage' exact={true} component={AcademicianPage}/>
                    <Route path='/Exams' exact={true} component={ExamList}/>
                    <Route path='/Exams/:id' component={ExamEdit}/>


                </Switch>
            </Router>
        )
    }
}

export default App;