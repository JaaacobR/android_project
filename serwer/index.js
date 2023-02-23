const express = require("express");
const formidable = require("formidable");

const app = express();
const fs = require("fs");

app.get("/", (req, res) => {
  res.send(`
    <h2>With <code>"express"</code> npm package</h2>
    <form action="/api/upload" enctype="multipart/form-data" method="post">
      <div>Text field title: <input type="text" name="title" /></div>
      <div>File: <input type="file" name="someExpressFiles" multiple="multiple" /></div>
      <input type="submit" value="Upload" />
    </form>
  `);
});

app.post("/api/upload", (req, res, next) => {
  const form = formidable({
    multiples: true,
    uploadDir: `${__dirname}/public/upload`,
    keepExtensions: true,
  });

  form.parse(req, (err, fields, files) => {
    if (err) {
      next(err);
      return;
    }
    res.send("yes");
  });
});

app.get("/getFile/:name", (req, res) => {
  let name = req.params.name;
  console.log(name);
  res.sendFile(__dirname + "/public/upload/" + name);
});

app.get("/getFiles", (req, res) => {
  console.log("getFiles");
  let resData = [];
  fs.readdir(__dirname + "/public/upload", function (err, files) {
    console.log(files);
    for (let i = 0; i < files.length; i++) {
      let stats = fs.statSync("public/upload/" + files[i]);
      resData.push({
        name: files[i],
        url: "/upload/" + files[i],
        size: stats.size,
        creationTime: stats.birthtime,
      });
    }
    res.send(JSON.stringify(resData));
  });
});

app.listen(3000, () => {
  console.log("Server listening on http://localhost:3000 ...");
});
