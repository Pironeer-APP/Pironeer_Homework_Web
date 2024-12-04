(function(){
  const canvas = document.querySelector('.stage');
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight
  const offscreenCanvas = canvas.transferControlToOffscreen();

  const worker = new Worker('/js/fireworks/src/fireworks.js' , { type : 'module'})
  worker.postMessage({msg: 'offscreen', canvas: offscreenCanvas}, [offscreenCanvas]);
})()