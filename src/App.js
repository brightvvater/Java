/*eslint-disable*/
import React, {useState} from 'react';
import './App.css';
import Modal from './Modal';

function App() {
  
  let [글제목,글제목변경] = useState(['남자 코트 추천','강남 우동 맛집','파이썬독학']);
  let [따봉, 따봉변경] = useState(0);
  let posts = '강남고기 맛집';
  function 함수() {
    return 100;
  }
  function 글변경() {
    var newArray = [...글제목];
    newArray[0] = '여자 코트 추천';
    글제목변경(newArray);
  }
  return (
    <div className="App">
      <div className='black-nav'>
        <div style={{color: 'blue', fontSize: '30px'}}>개발 Blog</div>
      </div>
      <button onClick={글변경}>버튼</button>
      {/* <img src={logo}/> */}
      {/* <h4>{posts}</h4>
      <h4>{함수()}</h4> */}
      <div className='list'>
        <h3>{글제목[0]} <span onClick={() => {따봉변경(따봉+1)}}>👍</span> {따봉} </h3>
        <p>2월 17일 발행</p>
        <hr/>
      </div>
      <div className='list'>
        <h3>{글제목[1]}</h3>
        <p>2월 17일 발행</p>
        <hr/>
      </div>
      <div className='list'>
        <h3>{글제목[2]}</h3>
        <p>2월 17일 발행</p>
        <hr/>
      </div>
      <Modal/>
      
    </div>
  );
}

export default App;
