package com.jovian.masterdetail

data class Superhero(
    var id:Int,
    var superhero:String,
    var publisher:String,
    var realName:String,
    var photo:String,
    var description:String
){
    companion object{
        var listSuper:MutableList<Superhero> = ArrayList()
        fun getSuperheros(): MutableList<Superhero>{
            listSuper.clear()
            listSuper.add(Superhero(1,"Spiderman", "Marvel", "Peter Parker", "spiderman.jpg",LoremIpsum().value))
            listSuper.add(Superhero(2,"Daredevil", "Marvel", "Matthew Michael Murdock", "daredevil.jpg",LoremIpsum().value))
            listSuper.add(Superhero(3,"Wolverine", "Marvel", "James Howlett", "logan.jpeg",LoremIpsum().value))
            listSuper.add(Superhero(4,"Batman", "DC", "Bruce Wayne", "batman.jpg",LoremIpsum().value))
            listSuper.add(Superhero(5,"Thor", "Marvel", "Thor Odinson", "thor.jpg",LoremIpsum().value))
            listSuper.add(Superhero(6,"Flash", "DC", "Jay Garrick", "flash.png",LoremIpsum().value))
            listSuper.add(Superhero(7,"Superman", "DC", "Clark Kent", "superman.jpg",LoremIpsum().value))
            listSuper.add(Superhero(8,"Iron Man", "DC", "Tonny Stark", "ironman.jpg",LoremIpsum().value))
            listSuper.add(Superhero(9,"Green Lantern", "DC", "Alan Scott", "green_lantern.jpg",LoremIpsum().value))
            listSuper.add(Superhero(10,"Wonder Woman", "DC", "Princess Diana", "wonder_woman.jpg",LoremIpsum().value))
            return listSuper
        }

        fun getSuperHeroById(mId: Int?):Superhero?{
            val superhero = getSuperheros().filter { superHero ->
                superHero.id==mId
            }

            return  superhero[0]?:null
        }

        fun getFirstID():Int{
            return getSuperheros()[0].id
        }
    }
}

