import React from 'react';
import { CountdownCircleTimer } from 'react-countdown-circle-timer'

const ExamInfos = props => {

    const minuteSeconds = 60;
    const stratTime = Date.now() / 1000; // use UNIX timestamp in seconds
    const duration = props.exam.examTime * minuteSeconds;
    const endTime = stratTime + duration; // use UNIX timestamp in seconds
    const getTimeMinutes = (time) => {
        return Math.floor(time / minuteSeconds) + ":" + Math.round(time % minuteSeconds);
    }

    const completed = () => {
        props.completed("Time's up!")
    }

    const remainingTime = endTime - stratTime;

    const renderTime = (dimension, time) => {
        return (
            <div className="time-wrapper">
                <div className="time">{time}</div>
                <div>{dimension}</div>
            </div>
        );
    };

    return (<div id="examInfo" className="examInfo">
        <div className="divExamInfoBody">
            <div className="examInfoRow">
                <div className="examInfoCell"><label>Name: </label></div>
                <div className="examInfoCell"><span>{props.exam.name}</span></div>
            </div>
            <div className="examInfoRow">
                <div className="examInfoCell"><label>Number of Questions: </label></div>
                <div className="examInfoCell"><span>{props.exam.numQuestions}</span></div>
            </div>
            <div className="examInfoRow">
                <div className="examInfoCell"><label>exam time: </label></div>
                <div className="examInfoCell"><span>{props.exam.examTime} minutes</span></div>
            </div>
            <div className="examInfoRow">
                <div className="examInfoCell"><label>Success: </label></div>
                <div className="examInfoCell"><span>{props.exam.success} %</span></div>
            </div>
        </div>
        {
            props.exam.examTime ? (
                <div className="Fixed">
                    <CountdownCircleTimer
                        isPlaying
                        duration={duration}
                        colors={[
                            ['#004777', 0.33],
                            ['#F7B801', 0.33],
                            ['#A30000', 0.33],
                        ]}
                        initialRemainingTime={remainingTime}
                        onComplete={completed}
                    >
                        {({ elapsedTime }) =>
                            renderTime("minutes", getTimeMinutes((props.exam.examTime * minuteSeconds) - elapsedTime))
                        }
                    </CountdownCircleTimer>
                </div>) : (<div></div>)
        }
    </div >)
}
export default ExamInfos;
