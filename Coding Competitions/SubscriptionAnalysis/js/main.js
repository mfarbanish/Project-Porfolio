//document.getElementById("getStarted").addEventListener("click", getFirstQ);



function getFirstQ() {
    //document.getElementById("getStarted").innerHTML = Date();
    window.location.href = "input.html";
}

function addSubscription(){
  
  var cat = document.getElementById("category").value;
  var serv = document.getElementById("service").value;
  var time = document.getElementById("time").value;
  var cost = document.getElementById("cost").value;
  arr.addEntry(serv,cat,cost,time);
  console.log(arr.data);
  window.location.href = "input.html";
}


class dataArray{
    constructor(){
      //this.arrdict = {};
      this.data = {};
      this.data2 = {};

      this.costarr = [];
      var i = 0;
      while (i <= 1000) {
          if (i%10 === 0) {
                  this.costarr.push(i);
              }
              i++;
      }


      this.usagearr = [];
      i = 0;
      while (i <= 500) {
          if (i%5 === 0) {
                  this.usagearr.push(i);
              }
              i++;
      }

      var catag = ["shopping", "entertainment", "communication", "entertainment",
                     "entertainment", "fitness", "entertainment", "entertainment", "professional", "home"];
      var names = ["Amazon", "Hulu", "Verizon", "Apple Music",
                     "Apple Care", "Gym", "New Yorker Subscription", "Netflix", "Adobe", "Blue Apron"];
      this.namearr = [];
      this.catarr = [];
      i = 0;
      while (i <= 10) {
          for (var j = 0; j < 10;j++){
              this.namearr.push(names[j]);
              this.catarr.push(catag[j]);
          }
          i++;
      }

      for (var k = 0; k < 100; k++){
          if (!(this.namearr[k] in this.data)){
              this.data[this.namearr[k]] = [this.catarr[k], this.costarr[k], this.usagearr[k]];
              
              this.data2[this.namearr[k]] = [this.catarr[k], [this.costarr[k]], [this.usagearr[k]]];
          }

          else {
              this.data[this.namearr[k]][1] += this.costarr[k];
              this.data[this.namearr[k]][2] += this.usagearr[k];
              
              this.data2[this.namearr[k]][1].push(this.costarr[k]);
              this.data2[this.namearr[k]][2].push(this.usagearr[k]);
              
          }
      }
    }

    addEntry(nam, cat, cos, usag){
        if (!(this.namearr[nam] in this.data)){
            this.data[nam] = [cat, cos, usag];
        }
        else {
            this.data[nam][1] += cos;
            this.data[nam][2] += usag;
            
            this.data2 = this.data2[nam][1].push(cos);
            this.data2 = this.data2[nam][2].push(cos);
        }
        return this.data;

  }
}
var arr = new dataArray()

function graph(){
  window.location.href = "plot.html";
  var data = {
    // A labels array that can contain any sort of values

    labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'],
    // Our series array that contains series objects or in this case series data arrays
    series: [
      [5, 2, 4, 2, 0]
    ]
  };

  // As options we currently only set a static size of 300x200 px. We can also omit this and use aspect ratio containers
  // as you saw in the previous example
  var options = {
    width: 300,
    height: 200
  };

  // Create a new line chart object where as first parameter we pass in a selector
  // that is resolving to our chart container element. The Second parameter
  // is the actual data object. As a third parameter we pass in our custom options.
  new Chartist.Line('.ct-chart', data, options);
}

function main(){
  arr = new dataArray()
  //console.log(arr.addEntry("Tone","fitness",43,8));
  console.log(arr.data);
  //graph()
}
//main()
