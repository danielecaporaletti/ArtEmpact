import React from 'react'

export default function ({hidden}) {
  return (
    <div className={`pt-[1.56rem] ml-[1.30rem] leading-[1.875rem] z-10 sm:mx-auto sm:w-full sm:flex sm:flex-col sm:justify-center sm:items-center ${hidden ? "hidden" : "block"}`}>
    <span className='text-left font-montserrat text-[1.625rem] font-[700] text-[#2F2F2F] mr-[5.19rem]'>L’arte come non <br/>l’avete mai pensata</span>
    <p className='text-left font-jost text-[0.875rem] font-[300] text-black leading-normal pt-[0.31rem] mr-[0.62rem]'>Il periodico di ArtEmpact per artisti che vogliono entrare <br />nel mondo del lavoro e per le imprese che vogliono <br/>includere gli artisti nei loro processi.</p>
    </div>
  )
}
