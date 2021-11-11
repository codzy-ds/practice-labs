import React, { useRef, useState } from 'react';
import Form from 'react-bootstrap/Form'
const Question = props => {

  const [flip, setFlip] = useState(false)

  const frontEl = useRef()
  const backEl = useRef()

  const questionAnswered = e => {
    props.changeScore(e);
    e.stopPropagation();
  }

  return (
    <article className={props.cardStyle} >
      <div
        className={`card ${flip ? 'flip' : ''}`}
        onClick={() => setFlip(!flip)}
      >
        <div className="front" ref={frontEl}>
          <div className="flashcard-options">
            <div className="flashcard-option">subject : {props.question.subject}</div>
            <Form.Check type="checkbox" label="Complété!" onClick={questionAnswered} />
          </div>
          {props.question.question}
        </div>
        <div className="back" ref={backEl}>{props.question.answer}</div>
      </div>
    </article >
  )
}
export default Question;
