import React, { useEffect, useState } from 'react';
import axios from "axios";
import Form from 'react-bootstrap/Form'
import ExamInfos from './ExamInfos'

const ExamsList = props => {

    const [exams, setExams] = useState([])

    useEffect(() => {
        axios.get(window._env_.API_URL.concat('/exams')).then(resp => {
            setExams(resp.data);
        }).catch(err => console.log(err))
    }, []);

    const examSelection = event => {
        exams.forEach(ex => {
            if (ex.name === event.target.value) {
                props.examChanged(ex);
            }
        })
    }

    return (
        <div id="examList" className="examList">
            <Form.Select size="lg" onChange={examSelection}>
                <option>&nbsp;</option>
                {exams.length > 0 ? (
                    exams.map(exam => (
                        <option key={exam.name}>{exam.name}</option>
                    ))
                ) : (<option>No exams</option>)}
            </Form.Select>
            <ExamInfos exam={props.exam} completed={props.completed} />
        </div>
    )
}

export default ExamsList;
