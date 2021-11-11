import React, { useState } from 'react';
import logo from './erlenmeyer-flask.jpeg';
import ExamsList from './components/ExamsList';
import axios from "axios";
import Questionnaire from './components/Questionnaire'
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';


function App() {

  const [questionnaire, setQuestionnaire] = useState({ exam: {}, questions: [] })
  const [showModal, setShowModal] = useState(false);
  const [completeStatus, setCompleteStatus] = useState("");
  const [score, setScore] = useState(0);
  const [exam, setExam] = useState([]);

  const examChanged = (examination) => {
    axios.get(window._env_.API_URL.concat('/questions/').concat(examination.name)).then(response => {
      setQuestionnaire(response.data);
      setExam(examination)
    }).catch(err => console.log(err))
  }

  const handleReset = () => {
    setQuestionnaire({ exam: {}, questions: [] })
    setExam({})
    setShowModal(false);
  };

  const handleClose = () => setShowModal(false);

  const testComlpleted = (status) => {
    setCompleteStatus(status)
    setShowModal(true);
  }

  const changeScore = (e) => {
    if (e.target.checked) {
      setScore(score + 1);
    } else {
      setScore(score - 1);
    }
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Practice Labs
        </p>
      </header>
      <div className="body">
        <ExamsList examChanged={examChanged} completed={testComlpleted} exam={exam} />
        {questionnaire.exam.name ?
          (<Questionnaire questions={questionnaire} changeScore={changeScore} />) : (<div>no exam</div>)
        }
      </div>
      <Modal
        show={showModal}
        onHide={handleClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>{completeStatus}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          You got a score of {Math.floor((score / questionnaire.questions.length) * 100)}%
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleReset}>
            Retake the Test
          </Button>
          <Button variant="primary" onClick={handleClose}>Revise</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default App;
