# software-quality

Overview of functional tests of Fintan

| Software modules    | test cases            | input sample          |  expected output  | actual output         |  
| :------------       |:--------------------: | ---------------------:| :------------     |:--------------------: | 
| Fintan backend      | Convert-CoNLL-to-RDF  |  [CoNLL](https://raw.githubusercontent.com/Pret-a-LLOD/software-quality-evaluation/main/Fintan/en-ud-dev.conllu)                | [RDF](https://raw.githubusercontent.com/Pret-a-LLOD/software-quality-evaluation/main/Fintan/conllToRdf.ttl)               | [CoNLL-RDF](https://raw.githubusercontent.com/Pret-a-LLOD/software-quality-evaluation/main/Fintan/conllToRdf.ttl)             | 
| Fintan UI           | Convert-CoNLL-to-RDF  |   [CoNLL](https://raw.githubusercontent.com/Pret-a-LLOD/software-quality-evaluation/main/Fintan/en-ud-dev.conllu)                | [RDF](https://raw.githubusercontent.com/Pret-a-LLOD/software-quality-evaluation/main/Fintan/conllToRdf.ttl)               | [CoNLL-RDF (Failed)]                      
| Terme-à-LLOD        | Convert-TBX-to-RDF    |   [solarTBX](https://raw.githubusercontent.com/Pret-a-LLOD/software-quality-evaluation/main/Term-a-llod/solar.tbx)                | [solarRDF](quora.com/profile/Ashish-Kulkarni-100)               | [solarRDF](https://github.com/Pret-a-LLOD/software-quality-evaluation/blob/main/Term-a-llod/solar.ttl) 
| Terme-à-LLOD        | Convert-TBX-to-RDF    |   [iateTBX]               | [iateRDF]            | [iateRDF] 
