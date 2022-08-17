import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';
import AcademicianList from "./AcademicianList";

class LessonList extends Component {

    constructor(props) {
        super(props);
        this.state = {lessons: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/Lessons')
            .then(response => response.json())
            .then(data => this.setState({lessons: data}));
    }

    async remove(id) {
        await fetch(`Lessons/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedLessons = [...this.state.lessons].filter(i => i.id !== id);
            this.setState({lessons: updatedLessons});
        });
    }

    render() {
        const {lessons} = this.state;

        const lessonList = lessons.map(lesson => {
            return <tr key={lesson.id}>
                <td style={{whiteSpace: 'nowrap'}}>{lesson.lessonName}</td>
                <td>{lesson.startTimeSlot}</td>
                <td>{lesson.endTimeSlot}</td>
                <td>{lesson.academicianId}</td>

                <td>
                    <ButtonGroup>
                        <Button size="sm" color="warning" tag={Link} to={"/Lessons/" + lesson.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(lesson.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <br/>
                    <h3>Lesson Edit Page</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Lesson Name</th>
                            <th width="20%">Lesson Start Time</th>
                            <th width="20%">Lesson End Time</th>
                            <th width="20%">academicianId</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {lessonList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        <Button color="dark" tag={Link} to="/Lessons/new">Add Lesson</Button>
                    </div>
                </Container>
            </div>
        );
    }
}

export default LessonList;