// JavaScript Document

function $(element){
return element = document.getElementById(element);
}
function $D(){
var d=$('list_db');
var h=d.offsetHeight;
var maxh=100;
function dmove(){
h+=1; //层展开速度
if(h>=maxh){
d.style.height='100px';
clearInterval(iIntervalId);
}else{
d.style.display='block';
d.style.height=h+'px';
}
}
iIntervalId=setInterval(dmove,2);
}
function $D2(){
var d=$('list_db');
var h=d.offsetHeight;
var maxh=100;
function dmove(){
h-=1;//层收缩速度
if(h<=0){
d.style.display='none';
clearInterval(iIntervalId);
}else{
d.style.height=h+'px';
}
}
iIntervalId=setInterval(dmove,2);
}
function $use(){
var d=$('banner_contenner');
var sb=$('open-close');
if(d.style.display=='none'){
$D();
sb.innerHTML='关闭';
}else{
$D2();
sb.innerHTML='打开';
}
}
