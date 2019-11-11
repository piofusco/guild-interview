context('Journey spec', () => {
    it('User can open ', () => {
        cy.visit('/')

        cy.get('Type your message here!').click()
        cy.type('Wow, this is such an amazing app - we should hire this guy!')
        cy.get('button[value="Submit"]').click()

        cy.contains('Wow, this is such an amazing app - we should hire this guy!').should('exist')

        expect(true).to.equal(false)
    })
})