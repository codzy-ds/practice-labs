import React, { useState } from 'react';
import Question from './Question';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faChevronLeft,
    faChevronRight,
} from "@fortawesome/free-solid-svg-icons";

const Questionnaire = props => {

    const [index, setIndex] = useState(0);

    const slideLeft = () => {
        console.log("slide left")
        setIndex(index - 1);
    };

    const slideRight = () => {
        console.log("slide right")
        setIndex(index + 1);
    };

    return (<div className="questoinnaire-section">
        <div className="examHeader"><h2>{props.questions.exam.name}</h2>
        </div>
        <div className="questionnaire-nav">
            <FontAwesomeIcon
                onClick={slideLeft}
                className="leftBtn"
                icon={faChevronLeft}
                size="lg"
                color="blue"
            />
            <FontAwesomeIcon
                onClick={slideRight}
                className="rightBtn"
                icon={faChevronRight}
                size="lg"
                color="blue"
            />
        </div>
        <div className="questionnaire">
            {
                props.questions.questions.length > 0 ?
                    (props.questions.questions.map((q, n) => {
                        let position = n > index ? "nextCard" : n === index ? "activeCard" : "prevCard";
                        return <Question question={q} index={n} changeScore={props.changeScore} cardStyle={position} key={n} />
                    })) :
                    (<div>list empty</div>)
            }
        </div>

    </div >)
}

export default Questionnaire;
