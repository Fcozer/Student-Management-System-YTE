import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AcademicianNavbar from "./AcademicianNavbar";
import { Link } from 'react-router-dom';


class ExamList extends Component {
//props component arası ilişkiyi sağlar
    constructor(props) {
        super(props);
        this.state = {exams: []};
        this.remove = this.remove.bind(this);
    }
//Component Dom'a render edildikten sonra çalışıyor
    componentDidMount() {
        fetch('/Exams')
            .then(response => response.json())
            .then(data => this.setState({exams: data}));
    }
//fetch ile uzantıya erişiyorum ve async,await birlikte kullanılır asenkron yapının daha kolay anlaşılmasını sağlar.
    async remove(id) {
        await fetch(`Exams/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedExams = [...this.state.exams].filter(i => i.id !== id);
            this.setState({exams: updatedExams});
        });
    }

    render() {
        const {exams} = this.state;

        const examList = exams.map(exam => {
            return <tr key={exam.id}>
                <td style={{whiteSpace: 'nowrap'}}>{exam.examClass}</td>
                <td>{exam.startTimeSlot}</td>
                <td>{exam.endTimeSlot}</td>
                <td>{exam.lessonId}</td>

                <td>
                    <ButtonGroup>
                        <Button size="sm" color="warning" tag={Link} to={"/Exams/" + exam.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(exam.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AcademicianNavbar/>
                <Container fluid>
                    <br/>
                    <h3>Exam Edit Page</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Exam Name</th>
                            <th width="20%">Exam Start Time</th>
                            <th width="20%">Exam End Time</th>
                            <th width="20%">lessonId</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {examList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        <Button color="dark" tag={Link} to="/Exams/new">Add Exam</Button>
                    </div>
                </Container>
            </div>
        );
    }
}

export default ExamList;